package ngo.drc.locale;

public class LocaleContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setLocale(String locale) {
        contextHolder.set(locale);
    }

    public static String getLocale() {
        if (contextHolder.get() == null) {
            return "en";
        }
        return contextHolder.get();
    }

    public static void clearLocale() {
        contextHolder.remove();
    }
}