package tppaMD_logicaDados;

import java.io.Serializable;

//esta class simula a estadia numa estação espacial
//cada uma das variaveis indica se é possivel fazer as suas ações limitadas
//quando se faz uma das ações limitadas deve colocar-se a variavel que a define a false
//por exemplo, só se pode contratar umm officer se a variavel "	Hire" estiver true
//após a contratação, deve colocar-se a variavel hire a false
public class SpaceStation implements Serializable{
	
	
	private boolean CargoUpgrade = true;
	private boolean ConvertCargo = true;
	private boolean Hire = true;
	private boolean HealLandingCraft = true;
	
	
	public SpaceStation() {}


	public boolean isCargoUpgrade() {
		return CargoUpgrade;
	}
	public void setCargoUpgrade(boolean cargoUpgrade) {
		CargoUpgrade = cargoUpgrade;
	}
	public boolean isConvertCargo() {
		return ConvertCargo;
	}
	public void setConvertCargo(boolean convertCargo) {
		ConvertCargo = convertCargo;
	}
	public boolean isHire() {
		return Hire;
	}
	public void setHire(boolean hire) {
		Hire = hire;
	}
	
	public boolean isHealLandingCraft() {
		return HealLandingCraft;
	}
	public void setHealLandingCraft(boolean healLandingCraft) {
		HealLandingCraft = healLandingCraft;
	};
	
	
	
}
