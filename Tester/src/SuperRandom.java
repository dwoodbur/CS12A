public class SuperRandom {

    int offSet = 0;
    //random number list
    int[] randomers = {12,24,84,-200,-800,86,-1000,20,600,1,2,6,
    78,62,34,76,22,-96,84,-200,-822,86,-100,12,12,24,84,-200,-800,86,-1000,20,600,1,2,6,
    78,62,34,76,22,-96,84,-200,-822,86,-100,12,12,24,84,-200,-800,86,-1000,20,600,1,2,6,
    78,62,34,76,22,-96,84,-200,-822,86,-100,12,12,24,84,-200,-800,86,-1000,20,600,1,2,6,
    78,62,34,76,22,-96,84,-200,-822,86,-100,12};
    SuperRandom()
    {}

    //returns a random integer based on the numberOfQuestions...not very saavy.
    public int getNextRandom(int numberOfQuestions)
    {
        //increment and go go go...
        offSet +=1;
        return (randomers[(numberOfQuestions + offSet)]) ;
    }
}