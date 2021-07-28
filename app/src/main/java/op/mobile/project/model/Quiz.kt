/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.model

/**
 * Data entity class of Quiz
 *
 * @author Hua Wang
 */
data class Quiz (
    val country: String,
    val questions: List<Question>
)