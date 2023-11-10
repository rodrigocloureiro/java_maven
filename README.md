### Enunciado TP1
Você deverá criar um projeto maven com o archetype quickstart.

- Esse projeto deverá rodar dentro da Intelij ou eclipse. (Tirar print)

![](https://i.imgur.com/gl6LjMU.png)

- Esse projeto deverá ter pelo menos 3 packages com classes em cada um deles.

- As classes deverão utilizar métodos das classes de outros pacotes.

- Utilizar a biblioteca LOMBOK no seu projeto (ou qualquer outra de sua preferência) via dependências do Maven.

- Rode as Goals: clean, compile e package no seu projeto. (Tirar print da execução e do arquivo jar gerado)

**clean:**

![](https://i.imgur.com/mwQzXD2.png)

**compile:**

![](https://i.imgur.com/ZQmXB55.png)

**package:**

![](https://i.imgur.com/uhhatyY.png)

**jar gerado:**

![](https://i.imgur.com/dEHjWQb.png)

- Utilize o Debugger para verificar qualquer lógica do seu projeto. (Tirar print do código stopado no break point e de alguma variável no momento.)

![](https://i.imgur.com/8FA6Hyt.png)

- Com o Debugger parado alterar o valor de alguma variável. (Tirar print de antes e depois)

**antes:**

![](https://i.imgur.com/8FA6Hyt.png)

**depois:**

![](https://i.imgur.com/rBW1kfi.png)

- Criar algum breakpoint condicional (Tirar print dele parando e não parando)

**parando:**

![](https://i.imgur.com/c8qfuPh.png)

**não parando:**

![](https://i.imgur.com/QAuxM5X.png)

- Subir seu projeto no Github com pelo menos 2 branchs.

Observação: Subir seu projeto juntamente com os prints.

### Enunciado TP3

Parte 1
- Você deverá tirar um print screen do seu projeto no Github sem nenhuma tag.
- Crie uma branch do seu projeto chamada logs.
- Utilize o logback para substituir os System outs do seu projeto por mensagens de log.
- Utilize pelo menos uma mensagem em cada Level -> info, debug e error.
- Faça a merge da branch logs com a branch main quando finalizar o trabalho.
- Faça o push da branch logs para o github.

Parte 2
- Utilizando o Junit5 crie testes para o seu projeto.
- Utilize pelo menos uma assertion do tipo AssertThrows.
- Utilize o plugin Surefire para rodar os testes via Maven.(Commitar o print no github do relatório de execução).

Parte 3
- Gere uma Tag com a nova versão do seu projeto incluindo tudo que foi feito até aqui e suba essa tag para o Github.