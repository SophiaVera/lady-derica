package com.palyrobotics.frc2016.auto.actions;

import com.team254.lib.util.Pose;

/**
 * Waits for drivetrain to pass a specific distance
 * @author Team 254
 *
 */
public class WaitForDriveDistanceAction extends TimeoutAction {
    public double m_distance;
    public boolean m_positive;

    public WaitForDriveDistanceAction(double distance, boolean positive, double timeout) {
        super(timeout);
        m_distance = distance;
        m_positive = positive;

    }

    @Override
    public boolean isFinished() {
        Pose pose = drive.getPhysicalPose();
        double avg = (pose.getLeftDistance() + pose.getRightDistance()) / 2.0;
        return (m_positive ? avg >= m_distance : avg <= m_distance) || super.isFinished();
    }

}
