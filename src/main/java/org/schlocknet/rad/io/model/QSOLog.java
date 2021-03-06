package org.schlocknet.rad.io.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Represents a Logged QSO event
 */
@Data
@Entity
@Document(collection = "qso.logs")
public class QSOLog {

    /**
     * Unique Id of the record
     */
    @Id
    private UUID qsoId = UUID.randomUUID();

    /**
     * The callsign of the station / person who created this log record
     */
    private String callsign;

    /**
     * The callsign of the station that was contacted
     */
    private String stationWorked;

    /**
     * The time this QSO started (in UTC milliseconds since unix epoch)
     */
    private Long timeStart = System.currentTimeMillis();

    /**
     * The time (if recorded) the QSO ended (in UTC milliseconds since unix epoch)
     */
    private Long timeEnd;

    /**
     * The RF power (in Watt) used to transmit
     */
    private Double power;

    /**
     * Grid location of the station contacted
     */
    private String qth;

    /**
     * The RST report that was sent by the creator of this QSO record
     */
    private String rstSent;

    /**
     * The RST report that was received creator of this QSO record
     */
    private String rstReceived;

    /**
     * Frequency the QSO was made on
     */
    private String frequency;

    /**
     * The radio mode used during teh QSO (i.e. ssb, cw, JT65A, PSK31, etc)
     */
    private String mode;

    /**
     * Any comments related to the QSO
     */
    private String comments;
}
