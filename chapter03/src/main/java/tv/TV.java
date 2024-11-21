package tv;

public class TV {
	private int channel;
	private int volume;
	private boolean power;
	
	
	public TV() {
	}
	
	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}


	public int getChannel() {
		return channel;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isPower() {
		return power;
	}
	
	void power(boolean on) {
		this.power = on ? true : false; 
	}
	
	void channel(int channel) {
		int tmp = channel;
		if(tmp < 1) this.channel = 255;
		else if(tmp > 255) this.channel = 1;
		else this.channel = tmp;
	}
	
	void channel(boolean up) {
		if(up) channel(this.channel + 1);
		else channel(this.channel - 1);	
	}
	
	void volume(int volume) {
		int tmp = volume;
		if(tmp < 0) this.volume = 100;
		else if(tmp > 100) this.volume = 0;
		else this.volume = tmp;
	}
	
	void volume(boolean up) {
		if(up) volume(this.volume + 1);
		else volume(this.volume - 1);
	}
	
	public void status() {
		System.out.println("TV[channel=" + channel + ", volume=" + volume + ", power=" + (power ? "on" : "off") + "]");
	}
}
