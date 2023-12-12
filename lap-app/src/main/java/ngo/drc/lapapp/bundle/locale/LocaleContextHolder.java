package ngo.drc.lapapp.bundle.locale;

public class LocaleContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setLocale(String locale) {
        contextHolder.set(locale);
    }

    public static String getLocale() {
        return contextHolder.get();
    }

    public static void clearLocale() {
        contextHolder.remove();
    }
}