public interface CanInteract {

    abstract void startInteraction();
    abstract void affect(Asset willBeAffected, Asset cloned);
    abstract void beAffected(Asset affectedBy, Asset cloned);


}
