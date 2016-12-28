package com.palyrobotics.frc2016.auto.modes;

import com.palyrobotics.frc2016.auto.AutoMode;
import com.palyrobotics.frc2016.auto.AutoModeEndedException;
import com.palyrobotics.frc2016.behavior.routines.AutoAlignmentRoutine;
import com.palyrobotics.frc2016.behavior.routines.auto.DriveDistanceAction;
import com.palyrobotics.frc2016.behavior.routines.auto.RaiseShooterAction;
import com.palyrobotics.frc2016.behavior.routines.auto.ShootAction;
import com.palyrobotics.frc2016.subsystems.Drive.DriveGear;
import com.palyrobotics.frc2016.config.Constants;

/**
 * Crosses a B/D class defense
 * Attempts a high goal shot when contstructor parameter is true
 *
 */
public class CrossBDHighGoalMode extends AutoMode {
	private boolean mAttemptShot = false;
	public static final double mCompressorWaitTime = 3;

	/**
	 * Cross a B/D class defense
	 * @param attemptShot true to auto-align and shoot, false to only cross
	 */
	public CrossBDHighGoalMode(boolean attemptShot) {
		mAttemptShot = attemptShot;
	}
	
	@Override
	protected void routine() throws AutoModeEndedException {
		if(Constants.kRobotName == Constants.RobotName.TYR) {
			waitTime(mCompressorWaitTime);
			drive.setGear(DriveGear.HIGH);
		}
		
		runRoutine(new DriveDistanceAction(-Constants.kBreachDistance, 0.3));
		
		if(mAttemptShot && Constants.kRobotName == Constants.RobotName.TYR) {
			runRoutine(new AutoAlignmentRoutine());
			runRoutine(new RaiseShooterAction());
			runRoutine(new ShootAction());
		}
	}

	@Override
	public String toString() {
		return "Cross_BD" + ((mAttemptShot) ? "_HighGoal" : "");
	}

	@Override
	public void prestart() {
		System.out.println("Starting auto mode: " + toString());
	}
}
