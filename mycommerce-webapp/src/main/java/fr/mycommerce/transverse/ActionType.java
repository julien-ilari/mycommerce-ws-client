package fr.mycommerce.transverse;

public enum ActionType {
	CREATE(1), DEFAULT(0), UPDATE(2), DELETE(3);

	private int mode;

	private ActionType(int mode) {
		this.mode = mode;
	}

	public int getmode() {
		return this.mode;
	}

}
