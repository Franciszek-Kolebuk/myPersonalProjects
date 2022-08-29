package gamePackage;

import java.util.Random;

public class EnemySpawner {         //class used to spawn random enemies
    private Handler handler;
    private Vis vis;
    private Random r = new Random();

    private int scoreConst = 0;
    //private double spawnSpaceX = Math.random() * (CE203_1901839_Ass2.WIDTH+100 -100 + 1) -100;        //other method to make enemies appear in random places, yet not used
    //private double spawnSpaceY = Math.random() * (CE203_1901839_Ass2.HEIGHT+100 -100 + 1) -100;

    public EnemySpawner(Handler handler, Vis vis) {
        this.handler = handler;
        this.vis = vis;
    }
    public void time(){
        scoreConst++;

        if(scoreConst >= 100){
            scoreConst = 0;

            if(vis.getEnemiesNum()<50) {            //method to spawn more enemies with time
                if(vis.getScore()<500) {
                    vis.setEnemiesNum(vis.getEnemiesNum() + 1);
                    handler.addObject(new Enemy(r.nextInt(Covid_Frame_Game.WIDTH - 30), r.nextInt(Covid_Frame_Game.HEIGHT - 30), ID.Enemy));
                }
                else if (vis.getScore()<1000){
                    vis.setEnemiesNum(vis.getEnemiesNum() + 2);
                    handler.addObject(new Enemy(r.nextInt(Covid_Frame_Game.WIDTH - 30), r.nextInt(Covid_Frame_Game.HEIGHT - 30), ID.Enemy));
                    handler.addObject(new Enemy(r.nextInt(Covid_Frame_Game.WIDTH - 30), r.nextInt(Covid_Frame_Game.HEIGHT - 30), ID.Enemy));

                }
                else{
                    vis.setEnemiesNum(vis.getEnemiesNum() + 3);
                    handler.addObject(new Enemy(r.nextInt(Covid_Frame_Game.WIDTH - 30), r.nextInt(Covid_Frame_Game.HEIGHT - 30), ID.Enemy));
                    handler.addObject(new Enemy(r.nextInt(Covid_Frame_Game.WIDTH - 30), r.nextInt(Covid_Frame_Game.HEIGHT - 30), ID.Enemy));
                    handler.addObject(new Enemy(r.nextInt(Covid_Frame_Game.WIDTH - 30), r.nextInt(Covid_Frame_Game.HEIGHT - 30), ID.Enemy));

                }
            }

        }

    }
}
