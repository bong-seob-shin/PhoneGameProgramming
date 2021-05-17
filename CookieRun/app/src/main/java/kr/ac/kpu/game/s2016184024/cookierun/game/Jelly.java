package kr.ac.kpu.game.s2016184024.cookierun.game;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016184024.cookierun.R;
import kr.ac.kpu.game.s2016184024.cookierun.framework.bitmap.IndexedGameBitmap;
import kr.ac.kpu.game.s2016184024.cookierun.framework.game.BaseGame;
import kr.ac.kpu.game.s2016184024.cookierun.framework.object.ImageObject;

public class Jelly extends ImageObject {
    private final IndexedGameBitmap ibmp;

    public Jelly(int index, float x, float y){
        super(R.mipmap.jelly, x, y);
        ibmp = new IndexedGameBitmap(R.mipmap.jelly,66,66,30,2,2 );
        ibmp.setIndex(index);

    }
    @Override
    public void update() {
        BaseGame game = BaseGame.get();
        float dx = Platform.SPEED * game.frameTime;
        dstRect.offset(-dx, 0);
        if(getRight()<0){
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
