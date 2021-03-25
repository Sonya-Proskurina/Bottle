package com.example.thebottle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.thebottle.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Random random;
    private int lastDir;
    private boolean spinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        random = new Random();

        //Загружаем картинку фона и бутылки
        Glide
                .with(this)
                .load("http://pngimg.com/uploads/bottle/bottle_PNG2951.png")
                .into(binding.bottle);

        Glide
                .with(this)
                .load("https://w-dog.ru/wallpapers/15/14/487406566296123/derevo-korichnevyj-tekstury-zabor-stena-chastokol.jpg")
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        binding.fon.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

    }

    public void bottleOnClick(View view) {
        // Условие не дает крутить бутылочку во время, когда она уже крутится
        if (!spinning) {
            //рандомное положение бутылочки
            int newDir = random.nextInt(1800);

            //анимация вращения
            Animation animation = new RotateAnimation(lastDir, newDir, (float) binding.bottle.getWidth()/2,(float) binding.bottle.getHeight()/2);
            animation.setDuration(2000);
            animation.setFillAfter(true);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning=true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning =false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastDir = newDir;
            binding.bottle.startAnimation(animation);
        }
    }
}