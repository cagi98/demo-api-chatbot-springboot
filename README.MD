# À propos

Ce projet est une API REST, une POC pour implémenter l'intégration d'un model LLM IA.
Cet API est un chatbot simple utilisant le model `tinyllama`.

Pour la démo, j'utilise `Ollama`, un outil open source pour faire tourner le LLM `tinyllama`.

## Comment tester ?

Simplement exécuter une requête HTTP `POST` vers `https://sprintboot-api-chatbot-demo.azurewebsites.net/v1/chat` avec :

- Une authentification de type basique: utiliser `Username: user` et `Password: user123`
- Un request body json comme suit:

  ```
    {
      "chatId": null,
      "question": "Quelle est la couleur du ciel par une journée ensoleillée ?"
    }
  ```
  
    Pour utiliser la même conversation (l'historique), récupérer la valeur de `chatId` dans la réponse retournée et l'utitliser pour une nouvelle
    requête comme suit:
    
    ```
    {
      "chatId": xxxxxx-xxxxx-xxxxxx-xxxxxx,
      "question": "Quelle est la couleur du ciel par une journée ensoleillée ?"
    }
  ```