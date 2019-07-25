package br.com.rmc.contaspagar.coreapi.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {

    public DateUtils() {
        throw new IllegalStateException("Classe utilitaria!!!");
    }

    public static Long diferencaEmDias(Date data, Date outraData) {
        if (data == null || outraData == null) {
            return Long.valueOf(0);
        }

        LocalDateTime ldt1 = data.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime ldt2 = outraData.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        Duration duracao = Duration.between(ldt1, ldt2);

        return duracao.toDays();
    }
}
