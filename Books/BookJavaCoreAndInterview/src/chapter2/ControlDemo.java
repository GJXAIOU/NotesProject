
class Computer
{
	private String cpuVersion;
	private String owner;
	
	public String getCpuVersion() {
		return cpuVersion;
	}
	public void setCpuVersion(String cpuVersion) {
		this.cpuVersion = cpuVersion;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public void start()
	{selfCheck();}
	
	private void selfCheck(){}
	
}


public class ControlDemo {

}
