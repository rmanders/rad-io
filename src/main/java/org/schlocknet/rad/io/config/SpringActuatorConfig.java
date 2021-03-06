package org.schlocknet.rad.io.config;

import java.io.IOException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.schlocknet.rad.io.model.ApplicationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringActuatorConfig
{
  /**
   * Local logger instance
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SpringActuatorConfig.class);

  private static final String UNKNOWN = "N/A";

  protected ApplicationInfo applicationInfo(Class clazz) {
    final String classPath = clazz.getResource(clazz.getSimpleName() + ".class").toString();

    if (!classPath.startsWith("jar")) {
      LOGGER.error("Class: [{}] is not from a jar. Unable to load manifest", clazz);
      return new ApplicationInfo(UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN);
    }

    final String manifestPath = classPath.substring(0, classPath.indexOf('!') + 1) +
        "/META-INF/MANIFEST.MF";

    Manifest manifest = null;
    try {
      manifest = new Manifest(new URL(manifestPath).openStream());
    } catch (IOException ex) {
      LOGGER.error("Unable to load jar manifest for classPath {} with manifest: {}", classPath, manifestPath, ex);
      return new ApplicationInfo(UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN);
    }

    Attributes attributes = manifest.getMainAttributes();
    return new ApplicationInfo(
        attributes.getValue("Specification-Title"),
        attributes.getValue("Implementation-Version"),
        System.getProperty("spring.profiles.active", "unknown"),
        attributes.getValue("Build-Date")
    );
  }
}
