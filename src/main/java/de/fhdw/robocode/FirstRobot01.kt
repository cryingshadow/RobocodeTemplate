package de.fhdw.robocode

import robocode.*
import robocode.util.Utils
import java.awt.Color
import java.awt.geom.Point2D
import kotlin.math.*


data class Enemy(
    val position: Pair<Double, Double>,
    val heading: Double,
    val headingChange: Double,
    val velocity: Double,
)

class FirstRobot01 : AdvancedRobot() {
    private var enemyTarget: Enemy? = null

    private var oldHeading = 0.0

    override fun run() {
        val radius = 100.0
        val angle = 90.0

        isAdjustGunForRobotTurn = true
        isAdjustRadarForGunTurn = true

        setTurnRadarRight(Double.POSITIVE_INFINITY)
        setBulletColor(Color.GREEN)

        while (true) {
            if (enemyTarget == null) {
                setAhead(10.0)
                setTurnLeft(Utils.getRandom().nextDouble(30.0) - 15.0)
                setTurnRadarRight(Double.POSITIVE_INFINITY)
                //setAhead(20.0)
                //setTurnRadarRight(90.0)
                execute()
            } else {

               /* enemyTarget?.let {
                    val predictedPosition = it.position.first + cos(it.heading) * it.velocity to
                            it.position.second + sin(it.heading) * it.velocity

                    Utils.normalAbsoluteAngle()


                }

                setFireBullet(4.0)*/
                execute()

                //fireBullet(4.0)
                /*turnRadarLeft(10.0)
                turnRadarRight(20.0)*/
            }
            /*ahead(radius)
            turnLeft(angle)
            turnGunRight(5.0)
            fireBullet(energy)*/
        }
    }

    override fun onScannedRobot(e: ScannedRobotEvent) {
        val angleToEnemy = headingRadians + e.bearingRadians

        var radarTurn = Utils.normalRelativeAngle(angleToEnemy - radarHeadingRadians)
        val extraTurn = min(atan(24 / e.distance), Rules.RADAR_TURN_RATE_RADIANS);

        if (radarTurn < 0){
            radarTurn -= extraTurn
        } else {
            radarTurn += extraTurn
        }

        //fire(1.0)
        enemyTarget = Enemy(0.0 to 0.0, 0.0, 0.0, 0.0)
        setTurnRadarRightRadians(radarTurn)
        setTurnGunRightRadians(Utils.normalRelativeAngle(angleToEnemy - gunHeadingRadians))

        if (e.distance > 100.0) {
        }

        setFire(4.0)
        execute()


       /* val bulletPower = Math.min(3.0, energy)
        val myX = x
        val myY = y
        val absoluteBearing = headingRadians + e.bearingRadians
        val enemyX = x + e.distance * Math.sin(absoluteBearing)
        val enemyY = y + e.distance * Math.cos(absoluteBearing)
        var enemyHeading = e.headingRadians
        val enemyHeadingChange: Double = enemyHeading - oldHeading
        val enemyVelocity = e.velocity
        oldHeading = enemyHeading
        enemyTarget = Enemy(0.0 to 0.0, 0.0, 0.0, 0.0)

        var deltaTime = 0.0
        val battleFieldHeight = battleFieldHeight
        val battleFieldWidth = battleFieldWidth
        var predictedX = enemyX
        var predictedY = enemyY
        while (++deltaTime * (20.0 - 3.0 * bulletPower) <
            Point2D.Double.distance(myX, myY, predictedX, predictedY)
        ) {
            predictedX += Math.sin(enemyHeading) * enemyVelocity
            predictedY += Math.cos(enemyHeading) * enemyVelocity
            enemyHeading += enemyHeadingChange
            if (predictedX < 18.0 || predictedY < 18.0 || predictedX > battleFieldWidth - 18.0 || predictedY > battleFieldHeight - 18.0) {
                predictedX = Math.min(
                    Math.max(18.0, predictedX),
                    battleFieldWidth - 18.0
                )
                predictedY = Math.min(
                    Math.max(18.0, predictedY),
                    battleFieldHeight - 18.0
                )
                break
            }
        }
        val theta = Utils.normalAbsoluteAngle(
            Math.atan2(
                predictedX - x, predictedY - y
            )
        )

        setTurnRadarRightRadians(
            Utils.normalRelativeAngle(
                absoluteBearing - radarHeadingRadians
            )
        )
        setTurnGunRightRadians(
            Utils.normalRelativeAngle(
                theta - gunHeadingRadians
            )
        )
        setFire(3.0)
        execute()*/
    }

    override fun onHitWall(event: HitWallEvent?) {
        turnLeft(170.0)
        ahead(10.0)
    }

    override fun onHitByBullet(e: HitByBulletEvent) {
        //turnLeft(90 - e.bearing)
    }
}
