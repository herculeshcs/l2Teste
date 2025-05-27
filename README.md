# Solução para Questão de Pacotes

API para otimização de cálculo de pacotes. Este projeto implementa um endpoint único com suporte Docker, testes automatizados e documentação Swagger integrada.

## ✨ Funcionalidades

- **Endpoint único**: Rota `/pack` para cálculo otimizado de necessidades de pacotes
- **Dockerizada**: Solução pronta para execução em contêiner
- **Documentação Swagger**: Interface interativa de documentação da API
- **Testes automatizados**: Suíte completa de testes

## 🚀 Primeiros Passos

### Pré-requisitos
- Docker instalado

### Execução com Docker

1. **Construir a imagem**:
   
docker build -t l2teste .

2. **Executar imagem**:

docker run -p 8080:8080 l2teste

🌐 Acesso à API
Endpoint principal: http://localhost:8080/pack

Swagger UI: http://localhost:8080/swagger-ui/index.html
## Também existe o Repositorio com a imagem no Docker hub: 

https://hub.docker.com/r/hercules42/l2teste

## Clonando imagem docker hub:

docker pull hercules42/l2teste
