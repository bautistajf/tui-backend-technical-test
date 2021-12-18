package com.tui.proof.configuration;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Log4j2
@Setter
@ConfigurationProperties("spring.security.oauth2.client.provider.custom.resources")
public class Oauth2ConfigurationMethodParser {
    private static final Pattern PATH_WITH_METHOD = Pattern.compile("^([a-zA-Z]+)\\s+(.+)$");

    private List<String> publicPaths = new ArrayList<>();

    public Map<HttpMethod, Set<String>> getParsedPublicPaths() {
        Set<String> pathSet = new HashSet<>(publicPaths);
        return parsePaths(pathSet);
    }

    private Map<HttpMethod, Set<String>> parsePaths(final Set<String> pathSet) {
        Set<String> defaultSet = new HashSet<>();
        Map<HttpMethod, Set<String>> map = new HashMap<>();
        map.put(null, defaultSet);

        for (String path : pathSet) {
            Matcher m = PATH_WITH_METHOD.matcher(path);

            if (m.matches()) {
                String method = m.group(1);
                String rawPath = m.group(2);
                HttpMethod meth = HttpMethod.resolve(method);

                if (meth == null) {
                    // Invalid method
                    log.warn("Public path ignored: method {} not supported", method);
                } else if (meth.equals(HttpMethod.HEAD) || meth.equals(HttpMethod.OPTIONS) || meth.equals(HttpMethod.TRACE)) {
                    // Debug methods are not allowed
                    log.warn("Public path ignored: method {} not allowed", method);
                } else {
                    // Put the parsed path into the correct set
                    Set<String> set = map.computeIfAbsent(meth, k -> new HashSet<>());
                    set.add(rawPath);
                }
            } else {
                defaultSet.add(path);
            }
        }

        return map;
    }
}
