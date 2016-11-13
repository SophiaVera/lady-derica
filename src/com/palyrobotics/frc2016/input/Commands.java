package com.palyrobotics.frc2016.input;

import java.util.Optional;

import com.palyrobotics.frc2016.input.Commands.JoystickInput.XboxInput;

/**
 * Commands represent the desired setpoints and subsystem states for the robot. <br />
 * Store Requests (enum) for each subsystem and setpoints {@link Setpoints} <br />
 * Directly stores real or mock Joystick input in {@link JoystickInput}
 * @author Nihar
 *
 */
public class Commands {
	/**
	 * Stores all the subsystem setpoints
	 * @author Nihar
	 */
	public static class Setpoints {
		public static final Optional<Double> m_nullopt = Optional.empty();
		// Desired setpoints
		public Optional<Double> auto_align_setpoint = m_nullopt;
		public Optional<Double> encoder_drive_setpoint = m_nullopt;
		public Optional<Double> timer_drive_time_setpoint = m_nullopt;
		public Optional<Double> drive_velocity_setpoint = m_nullopt;
	}
	// All robot setpoints
	public Setpoints robotSetpoints;
	
	/**
	 * Class to store Joystick input
	 * Should eventually be extended to contain all buttons
	 * @author Nihar
	 */
	public static class JoystickInput {
		public static class XboxInput extends JoystickInput {
			public double leftX, leftY, rightX, rightY;
			public XboxInput(double leftX, double leftY, double rightX, double rightY) {
				super(leftX, leftY, false);
			}
		}
		public double x,y;
		public boolean triggerPressed;
		public JoystickInput(double x, double y, boolean triggerPressed) {
			this.x = x; this.y = y; this.triggerPressed = triggerPressed;
		}
		public JoystickInput(double x, double y) {
			this.x = x; this.y = y;
		}
	}
	// Stores Joystick values
	public JoystickInput leftStick;
	public JoystickInput rightStick;
	public XboxInput operatorStick;
	// Routine Request
	public static enum RoutineRequest {
		TIMER_DRIVE, ENCODER_DRIVE, TURN_ANGLE, AUTO_ALIGN, NONE
	}
	// Routine requests
	public RoutineRequest routineRequest;
	
	// Subsystem requests
	public IntakeRequest intakeRequest;
	public GrabberRequest grabber_request;
	public LatchRequest latch_request;
	public ShooterRequest shooter_request;
	public WinchRequest winch_request;
	public PinRequest pin_request;
	public LowGoalShooterRequest low_request;
	
	// Subsystem Requests
	public enum IntakeRequest {
		NONE, INTAKE, EXHAUST
	}
	public enum GrabberRequest {
		GRAB, RELEASE
	}
	public enum ShooterRequest {
		NONE, EXTEND, RETRACT
	}
	public enum LowGoalShooterRequest {
		NONE, SHOOT, LOAD
	}
	public enum LatchRequest {
		NONE, LOCK, UNLOCK
	}
	public enum WinchRequest {
		NONE, WIND, UNWIND
	}
	public enum PinRequest {
		NONE, LOCK, UNLOCK
	}
	// Allows you to cancel routine
	public boolean cancel_current_routine = false;
}