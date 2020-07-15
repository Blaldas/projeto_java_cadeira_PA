package tppaMD_logicaDados;

import java.io.Serializable;

//esta class simula a estadia numa esta��o espacial
//cada uma das variaveis indica se � possivel fazer as suas a��es limitadas
//quando se faz uma das a��es limitadas deve colocar-se a variavel que a define a false
//por exemplo, s� se pode contratar umm officer se a variavel "	Hire" estiver true
//ap�s a contrata��o, deve colocar-se a variavel hire a false
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
