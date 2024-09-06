# Teste técnico Itaú
Projeto de API Rest para localizar a posição de um PET

1. Clone o projeto com este comando git clone https://github.com/LucaaasCS/projeto-petfinder-itau.git

2. Instale as dependências e compile o projeto

3. Suba o projeto e abra seu Postman/Insomnia (ou a aplicação de sua preferência)

4. Coloque a URL: http://localhost:8090/api/sensor/location Obs*: A aplicação rodará na porta 8090 por padrão

5. Faça uma requisição Post como a do exemplo
   Ex: {
    "sensorId": "sensor123",
    "latitude": "47.735549",
    "longitude": "-94.548447",
    "dataHora": "2024-08-30T12:00:00"
}

<-------------------------------------------------------------------------------------------------------->


Documentação das Classes

1. SensorController
   
Responsabilidade: Controlador REST responsável por expor o endpoint /api/sensor/location. Ele recebe os dados do sensor, incluindo latitude e longitude, e retorna a localização geográfica utilizando uma API externa de geolocalização (PositionStack).

Métodos:
getLocationFromSensor(@RequestBody SensorData sensorData): Método que responde a requisições POST no endpoint /location. Ele recebe os dados do sensor como um SensorData via corpo da requisição, faz uma chamada ao serviço de geolocalização e retorna os dados da localização como uma PositionResponse.
Anotações:

@RestController: Define a classe como um controlador REST.
@RequestMapping("/api/sensor"): Define o mapeamento de requisição para o controlador.

2. SensorService
   
Responsabilidade: Serviço responsável por realizar a integração com a API de geolocalização externa (PositionStack), utilizando os dados de latitude e longitude fornecidos pelo sensor. Ele consome a API e retorna a resposta com os dados de localização.

Atributos:

RestTemplate restTemplate: Objeto utilizado para fazer a requisição HTTP à API externa.
POSITIONSTACK_API_URL: URL da API PositionStack, com o respectivo access key e parâmetros de consulta.
Métodos:

getGeolocation(String latitude, String longitude): Recebe latitude e longitude como parâmetros, formata a URL e faz a chamada à API utilizando o RestTemplate. Retorna um objeto PositionResponse com os dados da localização obtidos da API.
Anotações:

@Service: Define a classe como um serviço no Spring Framework.

3. SensorData

Responsabilidade: Classe modelo que representa os dados recebidos de um sensor. Os dados incluem informações como a latitude, longitude, ID do sensor e a data/hora da coleta.

Atributos:
String sensorId: ID único do sensor.
String latitude: Latitude coletada pelo sensor.
String longitude: Longitude coletada pelo sensor.
String dataHora: Data e hora em que a leitura foi feita.
Anotações:

@Data: Gera automaticamente os métodos getters, setters, equals, hashCode e toString com o uso do Lombok.

4. DataStack
   
Responsabilidade: Classe modelo utilizada para representar um único conjunto de dados de geolocalização retornados pela API PositionStack. Cada instância de DataStack contém informações detalhadas da localização, como país, região, área administrativa, bairro e rua.

Atributos:
String country: País da localização.
String region: Estado da localização.
String administrative_area: Área administrativa da localização.
String neighbourhood: Bairro da localização.
String street: Endereço da rua.
Anotações:

@Data: Gera automaticamente os métodos getters, setters, equals, hashCode e toString com o uso do Lombok.

5. PositionResponse

Responsabilidade: Classe modelo que encapsula a resposta retornada pela API PositionStack. Ela contém uma lista de objetos DataStack que representam as diferentes partes da localização retornada pela API.

Atributos:
List<DataStack> data: Lista de objetos DataStack que representam os dados de geolocalização.
Anotações:

@Data: Gera automaticamente os métodos getters, setters, equals, hashCode e toString com o uso do Lombok.

6. Arquitetura e Integração
   
A arquitetura segue o padrão MVC (Model-View-Controller):
Controller (SensorController): Recebe a requisição HTTP, processa os dados de entrada e interage com o serviço.
Service (SensorService): Contém a lógica de negócio responsável por consumir a API externa e processar a resposta.
Model (SensorData, PositionResponse, DataStack): Define as classes de dados (entidades) utilizadas no projeto, tanto para a entrada dos dados (SensorData) quanto para a resposta da API (PositionResponse e DataStack).

7. API Externa Utilizada

PositionStack API: A API externa utilizada para fazer a geolocalização reversa, recebendo latitude e longitude e retornando informações detalhadas sobre a localização (país, região, bairro, etc.). A chamada à API é feita utilizando o RestTemplate no SensorService.
URL da API: http://api.positionstack.com/v1/reverse?access_key=5ba395086fa2ab8cc4257d71ef969bbe&query={latitude},{longitude}

