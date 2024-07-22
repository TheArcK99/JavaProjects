import java.awt.*;//Canvas, Dimension
        import javax.swing.JFrame;//JFrame
        import java.awt.image.BufferStrategy;
        import java.awt.event.*;
        import java.util.*;
public class KansupadaA19
{
    public static void main(String[] args)
    {
        ColorGrid.main(args);
    }
}
//Familiarize yourself with the Box class.  A Box represents one of the colored squares in the grid.
//The variables x and y represent the Box's position in the 2D array, not its position on the screen
class Box
{
    static Color[] colors = {Color.red, Color.blue, Color.yellow, Color.magenta, Color.cyan, Color.white};
    Color color;
    int x,y;

    public Box(int row, int col)
    {
        newColor();
        x=col;
        y=row;
    }
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x*50+1,y*50+1,48,48);
    }
    public void newColor()
    {
        color = colors[(int)(Math.random()*colors.length)];
    }
    public String toString()
    {
        return x+","+y+"\t"+color;
    }
}

class ColorGrid extends Canvas implements Runnable
{
    //Do not modify any other methods in this class other than fillColor
    public static void main(String args[]){	new ColorGrid();}
    public void fillColor(Box b) {


        boolean[][] visit = new boolean[8][8];
        visit[b.y][b.x] = true;
        Queue<Box> adds = new LinkedList<>();
        adds.add(b);

        Color reference = grid[b.y][b.x].color;
        //System.out.println(reference);
        while(!adds.isEmpty())
        {
            Box e = adds.remove();
            grid[e.y][e.x].color = palette.color;
            if(e.y-1>-1 && visit[e.y-1][e.x] != true && grid[e.y-1][e.x].color.equals(reference))
            {
                adds.add(grid[e.y-1][e.x]);
                visit[e.y-1][e.x] = true;
            }
            if(e.y+1<8 && visit[e.y+1][e.x] != true && grid[e.y+1][e.x].color.equals(reference))
            {
                adds.add(grid[e.y+1][e.x]);
                visit[e.y+1][e.x] = true;
            }
            if(e.x-1>-1 && visit[e.y][e.x-1] != true && grid[e.y][e.x-1].color.equals(reference))
            {
                adds.add(grid[e.y][e.x-1]);
                visit[e.y][e.x-1] = true;
            }
            if(e.x+1<8 && visit[e.y][e.x+1] != true && grid[e.y][e.x+1].color.equals(reference))
            {
                adds.add(grid[e.y][e.x+1]);
                visit[e.y][e.x+1] = true;
            }

        }

        //this changes the paletter color (the selected color in the upper right of the window)
        //to a new random color
        palette.newColor();
    }


    private static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Box[][] grid = new Box[8][8];
    Box palette = new Box(1,11);
    Input mouse = new Input();
    public ColorGrid()
    {
        addMouseListener(mouse);
        requestFocus();
        for(int row=0;row<grid.length;row++)
            for(int col = 0;col<grid[0].length;col++)
                grid[row][col] = new Box(row,col);
        new Window(WIDTH, HEIGHT, "Color Fill", this);
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running =true;
    }
    public synchronized void stop()
    {
        try	{thread.join();running = false;}
        catch(Exception e){}
    }
    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 /amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames =0;
        while(running)
        {
            long now = System.nanoTime();
            delta+= (now - lastTime) / ns;
            lastTime = now;
            while(delta>=1){tick();delta--;}
            if(running)render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000)
            {	timer+=1000;System.out.println("FPS: "+frames);frames = 0;	}
        }
        stop();

    }
    public void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        palette.draw(g);
        for(Box[] row : grid)
            for(Box b: row)
            {
                b.draw(g);
                checkClick(b);
            }
        g.dispose();
        bs.show();
    }
    public void checkClick(Box b)
    {
        if(	mouse.x<b.x*50+48&&
                mouse.y<b.y*50+48&&
                mouse.x>b.x*50&&
                mouse.y>b.y*50)
        {
            fillColor(b);
            mouse.x=mouse.y=1000;
        }
    }
    public void tick(){	}
}
//do not modify this class
class Window extends Canvas
{
    public Window(int w, int h, String t, ColorGrid game)
    {
        JFrame frame = new JFrame(t);
        frame.setPreferredSize(new Dimension(w,h));
        frame.setMinimumSize(new Dimension(w,h));
        frame.setMaximumSize(new Dimension(w,h));

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);
        game.start();

    }
}
//do not modify this class
class Input extends MouseAdapter
{
    static int x,y;

    public void mouseClicked(MouseEvent e)
    {
        if(e.getX()!=x||e.getY()!=y)
        {

            x = e.getX();
            y=e.getY();

        }

    }
}
