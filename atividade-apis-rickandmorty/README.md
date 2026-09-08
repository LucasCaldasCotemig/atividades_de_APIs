## Atividade de APIs — DEVAND (Desenvolvimento Android)

App Android nativo em Kotlin que consome a Rick and Morty API (https://rickandmortyapi.com/api/) para listar e detalhar personagens da serie, seguindo a mesma arquitetura em camadas usada em aula na Pokedex (Model / Network / ViewModel / UI).

### Principais telas/funcionalidades

- Lista de personagens com busca por nome (chama a API com o parametro name).
- - Ao tocar em um personagem, abre a tela de detalhes com imagem, status, especie, genero, origem e localizacao atual.
  - - Estados de tela tratados de forma explicita: carregando, sucesso e falha (sem internet, busca sem resultado ou erro inesperado) — o app nunca fecha ou trava.
   
    - ### Arquitetura
   
    - app/src/main/java/com/lucao/rickandmorty/
    - - model/       data classes do JSON da API (CharacterDto, InfoDto, LocationRefDto, CharacterResponseDto)
      - - network/     interface Retrofit (RickAndMortyApiService) e client (RetrofitInstance)
        - - viewmodel/   CharacterViewModel + sealed interface CharacterListUiState
          - - ui/          Telas em Jetpack Compose (lista, detalhe) e navegacao
           
            - A UI nunca chama o Retrofit diretamente: ela so observa o StateFlow exposto pelo CharacterViewModel e dispara acoes (searchCharacters, selectCharacter) por callback. As chamadas de rede rodam em funcoes suspend dentro do viewModelScope, fora da Main Thread.
           
            - ### API escolhida
           
            - Rick and Morty API — publica, gratuita, sem autenticacao. Endpoint usado: GET character?name={query}.
           
            - ### Como rodar no Android Studio
           
            - 1. Abra a pasta atividade-apis-rickandmorty no Android Studio (Open project).
              2. 2. Aguarde a sincronizacao do Gradle (as dependencias sao baixadas automaticamente: Retrofit, Gson, Coroutines, Navigation Compose, Coil).
                 3. 3. Rode em um emulador ou dispositivo com API 24+.
                    4. 4. E necessario acesso a internet no dispositivo/emulador (permissao ja declarada no AndroidManifest.xml).
                       5. 
