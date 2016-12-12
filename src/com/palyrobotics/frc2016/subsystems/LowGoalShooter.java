package com.palyrobotics.frc2016.subsystems;

import com.palyrobotics.frc2016.config.Commands;
import com.palyrobotics.frc2016.config.RobotState;
import com.palyrobotics.frc2016.util.Subsystem;

/**
 * Represents Derica low goal shooter flywheel
 */
public class LowGoalShooter extends Subsystem implements SubsystemLoop {
	// Stores output to return
	private double output = 0.0;
	public enum WantedLowGoalState {
		SHOOTING, INTAKING, STOP
	}
	private WantedLowGoalState mWantedState = WantedLowGoalState.STOP;

	public LowGoalShooter() {
		super("LowGoalShooter");
	}

	/**
	 * Get the PWM signal for the low goal shooter
	 * @return PWM value for the flywheel
	 */
	public double get() {
		return output;
	}

	@Override
	public void onStart() {
		output = 0.0;
	}

	@Override
	public void update(Commands commands, RobotState robotState) {
		if (commands.low_request == Commands.LowGoalShooterRequest.LOAD) {
			mWantedState = WantedLowGoalState.INTAKING;
		}
		else if (commands.low_request == Commands.LowGoalShooterRequest.SHOOT) {
			mWantedState = WantedLowGoalState.SHOOTING;
		}
		else {
			mWantedState = WantedLowGoalState.STOP;
		}
		switch (this.mWantedState) {
			case INTAKING:
				output = -0.7;
				break;
			case SHOOTING:
				output = 1.0;
				break;
			case STOP:
				output = 0.0;
				break;
			default:
				break;
		}
	}

	@Override
	public void onStop() {
		output = 0.0;
	}
}
