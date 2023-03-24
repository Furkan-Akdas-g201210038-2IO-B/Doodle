public interface VelocityGiver {


        default void giveVelocity(VelocityTaker velocityTaker,Velocity velocity){
            velocityTaker.takeVelocity(velocity);
        }

}
