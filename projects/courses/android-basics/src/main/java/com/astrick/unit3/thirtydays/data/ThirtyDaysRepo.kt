package com.astrick.unit3.thirtydays.data

import androidx.annotation.DrawableRes
import com.astrick.androidbasicscompose.R

data class InspirationalQuote(
    val day: Int,
    val title: String,
    val content: String,
    @DrawableRes val drawableId: Int
)

object ThirtyDaysRepo {
    val quotes = listOf(
        InspirationalQuote(
            day = 1,
            title = "The Power of Persistence",
            content = "Success is not final, failure is not fatal: It is the courage to continue that counts.",
            drawableId = R.drawable.affirmations1
        ),
        InspirationalQuote(
            day = 2,
            title = "Dream Big, Work Hard",
            content = "The future belongs to those who believe in the beauty of their dreams.",
            drawableId = R.drawable.affirmations2
        ),
        InspirationalQuote(
            day = 3,
            title = "Embrace the Journey",
            content = "Life is a journey that must be traveled no matter how bad the roads and accommodations.",
            drawableId = R.drawable.affirmations3
        ),
        InspirationalQuote(
            day = 4,
            title = "Rise Above Adversity",
            content = "In the middle of every difficulty lies opportunity.",
            drawableId = R.drawable.affirmations4
        ),
        InspirationalQuote(
            day = 5,
            title = "Unleash Your Potential",
            content = "You are never too old to set another goal or to dream a new dream.",
            drawableId = R.drawable.affirmations5
        ),
        InspirationalQuote(
            day = 6,
            title = "Find Your Inner Strength",
            content = "Believe you can, and you're halfway there.",
            drawableId = R.drawable.affirmations6
        ),
        InspirationalQuote(
            day = 7,
            title = "Inspiration in Every Day",
            content = "Every day may not be good, but there's something good in every day.",
            drawableId = R.drawable.affirmations7
        ),
        InspirationalQuote(
            day = 8,
            title = "The Art of Resilience",
            content = "Rock bottom became the solid foundation on which I rebuilt my life.",
            drawableId = R.drawable.affirmations8
        ),
        InspirationalQuote(
            day = 9,
            title = "Chase Your Passion",
            content = "Follow your passion; it will lead you to your purpose.",
            drawableId = R.drawable.affirmations9
        ),
        InspirationalQuote(
            day = 10,
            title = "Seize the Moment",
            content = "Carpe Diem. Seize the day, boys. Make your lives extraordinary.",
            drawableId = R.drawable.affirmations10
        ),
        InspirationalQuote(
            day = 11,
            title = "Strength in Unity",
            content = "Alone, we can do so little; together, we can do so much.",
            drawableId = R.drawable.affirmations1
        ),
        InspirationalQuote(
            day = 12,
            title = "Embrace Change",
            content = "The only way to make sense out of change is to plunge into it, move with it, and join the dance.",
            drawableId = R.drawable.affirmations2
        ),
        InspirationalQuote(
            day = 13,
            title = "The Gift of Today",
            content = "Yesterday is history, tomorrow is a mystery, today is a gift of God, which is why we call it the present.",
            drawableId = R.drawable.affirmations3
        ),
        InspirationalQuote(
            day = 14,
            title = "Believe in Yourself",
            content = "You are braver than you believe, stronger than you seem, and smarter than you think",
            drawableId = R.drawable.affirmations4
        ),
        InspirationalQuote(
            day = 15,
            title = "Create Your Legacy",
            content = "The best way to predict your future is to create it",
            drawableId = R.drawable.affirmations5
        ),
        InspirationalQuote(
            day = 16,
            title = "Courageous Hearts",
            content = "Courage is resistance to fear, mastery of fear, not the absence of fear",
            drawableId = R.drawable.affirmations6
        ),
        InspirationalQuote(
            day = 17,
            title = "The Beauty of Simplicity",
            content = "Simplicity is the ultimate sophistication.",
            drawableId = R.drawable.affirmations7
        ),
        InspirationalQuote(
            day = 18,
            title = "Leap of Faith",
            content = "Take the first step in faith. You don't have to see the whole staircase, just take the first step.",
            drawableId = R.drawable.affirmations8
        ),
        InspirationalQuote(
            day = 19,
            title = "Dare to Dream",
            content = "All our dreams can come true if we have the courage to pursue them.",
            drawableId = R.drawable.affirmations9
        ),
        InspirationalQuote(
            day = 20,
            title = "A World of Possibilities",
            content = "The only limit to our realization of tomorrow will be our doubts of today.",
            drawableId = R.drawable.affirmations10
        ),
        InspirationalQuote(
            day = 21,
            title = "Respect and Kindness",
            content = "In a world where you can be anything, be kind.",
            drawableId = R.drawable.affirmations1
        ),
        InspirationalQuote(
            day =22,
            title = "Inner Peace",
            content = "Peace comes from within. Do not seek it without.",
            drawableId = R.drawable.affirmations2
        ),
        InspirationalQuote(
            day = 23,
            title = "Elevate Your Mind",
            content = "The mind is not a vessel to be filled, but a fire to be kindled.",
            drawableId = R.drawable.affirmations3
        ),
        InspirationalQuote(
            day = 24,
            title = "Growth Through Adversity",
            content = "Out of difficulties grow miracles.",
            drawableId = R.drawable.affirmations4
        ),
        InspirationalQuote(
            day = 25,
            title = "Love Unconditionally",
            content = "Love all, trust a few, do wrong to none.",
            drawableId = R.drawable.affirmations5
        ),
        InspirationalQuote(
            day = 26,
            title = "The Strength of Character",
            content = "Character is the real foundation of all worthwhile success.",
            drawableId = R.drawable.affirmations6
        ),
        InspirationalQuote(
            day = 27,
            title = "Empower Your Imagination",
            content = "Imagination is more important than knowledge.",
            drawableId = R.drawable.affirmations7
        ),
        InspirationalQuote(
            day = 28,
            title = "The Spark of Creativity",
            content = "Creativity is intelligence having fun",
            drawableId = R.drawable.affirmations8
        ),
        InspirationalQuote(
            day = 29,
            title = "The Pursuit of Happiness",
            content = "Happiness is not something ready made. It comes from your own actions.",
            drawableId = R.drawable.affirmations9
        ),
        InspirationalQuote(
            day = 30,
            title = "Never Give Up",
            content = "Our greatest glory is not in never falling, but in rising every time we fall",
            drawableId = R.drawable.affirmations10
        ),
    )
}
