package pe.gob.onp.nsai.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sistema-nsai")
@Data
public class SistemaNSAIProperties {

    private Integer idSistemaFrontend;
    private Integer idSistemaServicios;
    private String urlFrontend;
    private String urlServidoresSaa;
    private String urlServidoresNsai;
    private String httpHeaderAccessControlMaxAge;
    private boolean aplicarSeguridadServicios;

}
