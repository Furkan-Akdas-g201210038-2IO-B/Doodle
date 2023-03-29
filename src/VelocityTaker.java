public interface VelocityTaker {

        void takeVelocity(Velocity givenVelocity, VelocityGiver velocityGiver, VelocityGiver cloned);

        void takeVelocityX(Velocity givenVelocityX, VelocityGiver velocityGiver, VelocityGiver cloned);
        void takeVelocityY(Velocity givenVelocityY, VelocityGiver velocityGiver, VelocityGiver cloned);

}
