package donjonDragon.plateau;

public class Position 
{
    private boolean m_obstacle;
    private TypeEntite m_combattant;
    
    public Position()
    {
        m_obstacle = false;
    }
    
    public boolean getObstacle()
    {
        return m_obstacle;
    }
    
    public void setObstacle(boolean obstacle)
    {
        m_obstacle = obstacle;
    }
}
