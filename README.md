# EmployeeAvaliation
A ideia desse projeto é criar uma plataforma para avaliar funcionários de uma empresa. 
É possível atribuir notas de performance para diferentes atributos dos funcionário, além de obter relatórios de médias por datas.
Os funcionários são dividos em setores, sendo o acesso para cada setor de funcionário controlado pelo nível do usuário logado.
É possível consultar o registro de mudanças para verificar qual usuário alterou qual atributo de qual funcionário em qual data.

## Processo de autenticação para testes via cliente HTTP
Na requisição post para o endpoint auth/register, envie um objeto com a seguinte estrutura:

    {
        "login": "GuilhermeTaliberti",
        "password": "Senha1234*",
        "userRole": "ADMIN"
    }
O request precisa retornar 200.

No endpoint auth/login, envie o objeto:

    {
        "login": "GuilhermeTaliberti",
        "password": "Senha1234*"
    }
O request vai retornar 200 e um token no body.

Pegue esse token e, nas outras requisições, vá até a aba Auth.
Crie o Auth do tipo Bearer Token e cole o token lá