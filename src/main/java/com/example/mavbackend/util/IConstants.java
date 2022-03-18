package com.example.mavbackend.util;

/**
 * Class for constants
 */

public final class IConstants {
    private IConstants() {
        throw new IllegalStateException("Utility class");
    }
    public static final String PATTERN_DATE_CLIENTE = "yyyy-MM-dd";
    public static final String ZONA_HORARIA_COLOMBIA = "America/Bogota";
    public static final String EDIT_MODE = "EDIT";
    public static final String INSERT_MODE = "INSERT";

    public static String USERROL = "MIEMBRO";
    public static String LEADERROL = "LÍDER";
    public static String ADMINISTRATOR = "ADMINISTRADOR";

//	public static int PUERTO_SERIDOR_CORREO = 465;
//	public static String NOMBRE_SERVIDOR_CORREO = "smtp.hostinger.com";

	public static int PUERTO_SERIDOR_CORREO = 587;
	public static String NOMBRE_SERVIDOR_CORREO = "smtp.office365.com";

}
