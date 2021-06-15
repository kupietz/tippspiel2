package com.github.zemke.tippspiel2.persistence.model

import com.github.zemke.tippspiel2.persistence.model.enumeration.FixtureStatus
import com.github.zemke.tippspiel2.service.NULL_TEAM_ID
import java.time.Instant
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Fixture(

    @Id var id: Long?,
    var date: Instant,
    @Enumerated(EnumType.STRING) var status: FixtureStatus,
    var matchday: Int,
    val goalsHomeTeam: Int?,
    val goalsAwayTeam: Int?,
    @ManyToOne(cascade = [CascadeType.MERGE]) var homeTeam: Team?,
    @ManyToOne(cascade = [CascadeType.MERGE]) var awayTeam: Team?,
    @ManyToOne(cascade = [CascadeType.MERGE]) var competition: Competition,
    /**
     * If `true`, will not be updated by external API.
     *
     * @see [com.github.zemke.tippspiel2.integration.FootballDataIntegrationConfig]
     */
    var manual: Boolean = false
) {

    fun complete() = homeTeam != null && awayTeam != null && homeTeam?.id != NULL_TEAM_ID && awayTeam?.id != NULL_TEAM_ID
}
