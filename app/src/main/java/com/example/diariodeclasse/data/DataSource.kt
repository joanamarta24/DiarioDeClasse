import kotlinx.coroutines.delay

data class Aluno(
    val nome: String,
    val foto: Int?, // Int para o ID do recurso drawable
    val curso: String
)

class DataSource {

    suspend fun carregarAlunos(): List<Aluno> {
        delay(1000)

        return listOf(
            Aluno(
                nome = "Rafael Costa",
                foto = com.example.diariodeclasse.R.drawable.account_circle,
                curso = "Análise e Desenvolvimento de Sistemas"
            ),
            Aluno(
                nome = "Maria Silva",
                foto = com.example.diariodeclasse.R.drawable.account_circle,
                curso = "Engenharia de Software"
            ),
            Aluno(
                nome = "João Pereira",
                foto = com.example.diariodeclasse.R.drawable.account_circle,
                curso = "Ciência da Computação"
            ),
            Aluno(
                nome = "Ana Souza",
                foto = com.example.diariodeclasse.R.drawable.account_circle,
                curso = "Sistemas de Informação"
            ),
            Aluno(
                nome = "Carlos Oliveira",
                foto = com.example.diariodeclasse.R.drawable.account_circle,
                curso = "Redes de Computadores"
            ),
            Aluno(
                nome = "Mariana Lima",
                foto = com.example.diariodeclasse.R.drawable.account_circle,
                curso = "Segurança da Informação"
            ),
            Aluno(
                nome = "Pedro Fernandes",
                foto = com.example.diariodeclasse.R.drawable.account_circle,
                curso = "Banco de Dados"
            ),
            Aluno(
                nome = "Juliana Gomes",
                foto = com.example.diariodeclasse.R.drawable.account_circle,
                curso = "Desenvolvimento Mobile"
            ),
            Aluno(
                nome = "Lucas Almeida",
                foto = com.example.diariodeclasse.R.drawable.account_circle,
                curso = "Inteligência Artificial"
            ),
            Aluno(
                nome = "Beatriz Rodrigues",
                foto = com.example.diariodeclasse.R.drawable.account_circle,
                curso = "Computação em Nuvem"
            )
        )
    }
}
