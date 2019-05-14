package lynbrook.sail.actor;

import java.awt.image.BufferedImage;

import lynbrook.sail.data.Constants;


public class Animation
{

    private BufferedImage[] mFrames;

    private int mCurrentFrame;

    private int mDelayCount;

    private int mDelaySum;


    public Animation()
    {
        mDelaySum = Constants.DEFAULT_ANIMATION_DELAY_FRAMES;
        mCurrentFrame = 0;
        mDelayCount = 0;
    }


    public void setFrames( BufferedImage[] frames )
    {
        mFrames = frames;
    }


    public void setDelay( int delaySum )
    {
        mDelaySum = delaySum;
    }


    public void update()
    {

        if ( mDelaySum < 0 )
        {
            return;
        }

        mDelayCount++;

        mDelayCount %= mDelaySum;
        if ( mDelayCount == 0 )
        {
            mCurrentFrame++;
        }

        mCurrentFrame %= mFrames.length;

    }


    public BufferedImage getImage()
    {
        return mFrames[mCurrentFrame];
    }

}