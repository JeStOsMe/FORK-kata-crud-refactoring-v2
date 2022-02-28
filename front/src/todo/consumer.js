const HOST_API = "http://localhost:8080/api/";

export default {
    findAll: async (listId) => {
        return fetch(HOST_API + listId + "/todos")
                .catch(error => console.error("ERROR: ", error))
    },

    save: async (listId, request) => {
        return fetch(HOST_API + listId + "/todo", {
            method: "POST",
            body: JSON.stringify(request),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .catch(error => console.error("ERROR: ", error))
    },

    update: async (listId, request) => {
        return fetch(HOST_API + listId + "/todo", {
            method: "PUT",
            body: JSON.stringify(request),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .catch(error => console.error("ERROR: ", error))
    }, 

    delete: async (id) => {
        return fetch(HOST_API + id + "/todo", {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .catch(error => console.error("ERROR: ", error))
    }, 
};