package utils;

import entity.Meteor;
import entity.bullets.Bullet;
import entity.plane.EnemyPlane;
import entity.plane.Plane;
import enums.BulletType;
import enums.Dire;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Factory {
    private static Random random = new Random();

    /**
     * @param plane 为哪架飞机生成子弹
     * @param list  存放子弹的集合
     */
    public static void generateBullet(Plane plane, List<Bullet> list, BulletType bulletType) {
        Bullet bullet = new Bullet();
        bullet.setY(plane.getY() - 2);
        if (bulletType == BulletType.ENEMY || bulletType == BulletType.BOSS) {
            bullet.setImage(Medias.getImage("m9.png"));
            bullet.setSpeed(8);
            bullet.setY(plane.getY() + plane.getHeight() - 1);
        }

        bullet.setX(plane.getX() + (plane.getWidth() / 2) - bullet.getWidth() / 2);     //子弹的x坐标等于飞机的x坐标加上他宽度的一半再减去子弹宽度的一半
        bullet.setAttack(80);
        list.add(bullet);
    }

    public static void generateEnemyPlane(List<EnemyPlane> list) {

        int ran = random.nextInt(100);
        EnemyPlane plane = new EnemyPlane();
        if (ran < 20) {
            plane.setImage(Medias.getImage("enemy4.png"));
            plane.setDire(Dire.LEFT);
            plane.setX(Constants.WINDOW_WIDTH + plane.getWidth());
            plane.setY(random.nextInt(Constants.WINDOW_HEIGHT / 3) + 100);
            plane.setHp(100);
            plane.setMaxHp(100);
            plane.setSpeed(4);
            list.add(plane);
            return;
        }

        if (ran < 40) {
            plane.setImage(Medias.getImage("enemy1.png"));
            plane.setDire(Dire.RIGHT);
            plane.setX(-plane.getWidth());
            plane.setY(random.nextInt(Constants.WINDOW_HEIGHT / 3) + 100);
            plane.setHp(100);
            plane.setMaxHp(100);
            plane.setSpeed(4);
            list.add(plane);
            return;
        }

        if (ran < 100) {
            plane.setImage(Medias.getImage("enemy3.png"));
            plane.setDire(Dire.DOWN);
            plane.setX(random.nextInt(Constants.WINDOW_WIDTH - plane.getWidth() * 2) + plane.getWidth());
            plane.setY(-plane.getHeight());
            plane.setHp(200);
            plane.setMaxHp(200);
            plane.setSpeed(6);
            list.add(plane);
            return;
        }


    }

    public static void generateMeteor(List<Meteor> meteors) {
        Meteor meteor = new Meteor();
        meteor.setX(random.nextInt(Constants.WINDOW_WIDTH) - meteor.getWidth());
        meteor.setY(-meteor.getHeight());
        meteors.add(meteor);
    }


    //产生道具2中的友方战机
    public static void generateMyPlanes(List<EnemyPlane> myPlanes) {
        EnemyPlane plane = null;
        for (int i = 0; i < 10; i++) {
            plane = new EnemyPlane();
            plane.setImage(Medias.getImage("plane.png"));
            plane.setSpeed(8);
            plane.setDire(Dire.UP);
            plane.setX(random.nextInt(Constants.WINDOW_WIDTH - plane.getWidth()));
            plane.setY(random.nextInt(Constants.WINDOW_HEIGHT) + Constants.WINDOW_HEIGHT);
            myPlanes.add(plane);
        }
    }


    /**
     * @param type 要查询的成绩的参数
     * @return 得到的成绩
     */
    public static int getMaxScore(String type) {
        int max = 0;
        File file = null;
        FileReader fileReader = null;
        try {
            file = new File("conf/recored.properties");
            fileReader = new FileReader(file);
            Properties properties = new Properties();
            properties.load(fileReader);
            max = Integer.parseInt(properties.getProperty(type));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return max;
    }

    /**
     * 记录本地最佳成绩
     *
     * @param score 里程
     * @param type  记录的参数
     */
    public static void setMaxScore(int score, String type) {
        File file = new File("conf/recored.properties");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            Properties properties = new Properties();
            properties.load(fileReader);
            properties.setProperty(type, score + "");
            properties.store(new FileWriter(file), "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得一个从0至value的随机数
     *
     * @param value
     * @return
     */
    public static int random(int value) {
        return random.nextInt(value);
    }
}
