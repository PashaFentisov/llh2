package ngo.drc.references.bundle.locale;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LocaleFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String acceptLanguage = request.getHeader("Accept-Language");
        LocaleContextHolder.setLocale(acceptLanguage);
        try {
            filterChain.doFilter(request, response);
        } finally {
            LocaleContextHolder.clearLocale();
        }
    }
}