package com.example.mavbackend.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.beans.FeatureDescriptor;
import java.util.Map;
import java.util.stream.Stream;

/**
 * General and util methods
 */

public interface ITools {

    /**
     * Gets a PageRequest with Pageable and a map to get Sort fields
     *
     * @param pageable     -
     * @param clavesToSort -
     */
    static Pageable getPageRequest(Pageable pageable, Map<String, String> clavesToSort) {
        var sort = Sort.unsorted();
        for (Sort.Order s : pageable.getSort())
            sort = sort.and(Sort.by(s.getDirection(), clavesToSort.get(s.getProperty())));

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

    }

    /**
     * Setea uno a uno las propiedades del origen al destino, ignorando los campos
     * nulos
     *
     * @param aOrigenA
     * @param aDestinoB
     * @return aDestinoB
     */
    public static Object copiarPropiedadesObjetoAHaciaBIgnorandoNulosDeA(Object aOrigenA, Object aDestinoB) {
        String[] atributosIgnorados = getAtributosNulosDeObjeto(aOrigenA);
        BeanUtils.copyProperties(aOrigenA, aDestinoB, atributosIgnorados);
        return aDestinoB;
    }

    /**
     * Obtiene los campos nulos del objeto que se quiere analizar
     *
     * @param object
     * @return wrappedSource
     */
    private static String[] getAtributosNulosDeObjeto(Object object) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(object);
        return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
    }



}
