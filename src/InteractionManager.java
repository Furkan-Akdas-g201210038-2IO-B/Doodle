import java.util.ArrayList;

public class InteractionManager {

    private GamePanel gp;
    public ArrayList<Interaction> interactions=new ArrayList<>();
    private ArrayList<Interaction> deletedInteractions=new ArrayList<>();
    private final int initializedInteractionNum=100;
    public InteractionManager(GamePanel gp){

        this.gp=gp;

        for (int i=0;i<initializedInteractionNum;i++){
            addInteraction(new Interaction());
        }

    }
    private void addInteraction(Interaction interaction){
        interactions.add(interaction);
    }
    private Interaction getEmptyInteraction(){

        for (Interaction interaction : interactions){
            if(interaction.isThisEmpty())
                return interaction;
        }
        return null;
    }

    public Interaction findInteraction(Asset asset1,Asset asset2){

        for (Interaction interaction : interactions){
            if(interaction.equals(asset1,asset2))
                return interaction;

        }
        return null;
    }

    private boolean contains(Asset asset1,Asset asset2){
        for (Interaction interaction : interactions){
            if(interaction.equals(asset1,asset2))
                return true;

        }
        return false;
    }

    public void addInteraction(Asset asset1,Asset asset2,char type){

        Interaction interaction = getEmptyInteraction();

        if(!contains(asset1,asset2) && asset1 != asset2 ){
            interaction.setAsset1(asset1);
            interaction.setAsset2(asset2);
            interaction.setType(type);
        }

    }
    private void removeInteraction(Interaction interaction){

        interaction.setAsset1(null);
        interaction.setAsset2(null);
        interaction.setType('H');
    }
    public void removeInteraction(Asset asset1,Asset asset2){

        Interaction interaction = findInteraction(asset1,asset2);

        removeInteraction(interaction);
    }

    private void refresh(){

        for (Interaction interaction : interactions){
            if(interaction.getType()=='H'){
                removeInteraction(interaction);
            }
        }
    }

    public void startInteractionBetweenAssets(ArrayList<Asset> assets){

        for (Interaction interaction : interactions){
            if(interaction.getType()=='H'){
                //assets.whenHittingAsset()
            }else if(interaction.getType()=='C'){
                //assets.whenConnectingAsset()
            }
        }

        refresh();
    }

}
