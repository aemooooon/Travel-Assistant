/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.model

/**
 * Data entity class of Question
 *
 * @author Hua Wang
 */
data class Question(
    val id: Int,
    val question: String,
    val image: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
)