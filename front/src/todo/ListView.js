import React, {useContext, useEffect, useState} from "react";
import consumer from "./consumer";
import events from "./events";
import Store from "../Store"

export default( {listId, todo } ) => {
    const { dispatch } = useContext(Store);
    const { isLoaded, setLoaded } = useState(false);
    const list = todo.elements.filter((element) => {
        return element.listId === listId;
    });

    useEffect( () => {
        consumer.findAll(listId).then( (response) => {
            if(response.ok){
                response.json().then( (items) => {
                    console.log("successful TODO");
                    setLoaded(true);
                    dispatch(events.finded(listId, items))
                })
            }
        });
    }, [listId, dispatch]);

    const onDelete = (itemId) => {
        consumer.delete(itemId).then( (response) => {
            if(response.ok){
                dispatch(events.deleted(listId, itemId));
            }
        })
    };

    const onEdit = (item) => {
        dispatch(events.onEdited(listId, item))
    };

    const onChange = (event, item) => {
        const request = {
            name: item.name,
            id: item.id,
            completed: event.target.checked
        };
        consumer.update(listId, request).then( (response) => {
            if (response.ok){
                response.json().then( () => {
                    dispatch(events.updated(listId, request))
                })
            }
        });
    };

    const decorationDone = {
        TextDecoration: 'line-through',
        color: '#c3c3c3'
    };

    return <div>
        {!isLoaded && <div>Loading...</div>}
        <table >
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Tarea</td>
                    <td>¿Completado?</td>
                </tr>
            </thead>
            <tbody>
                {list.map( (todo) => {
                    return <tr key={todo.id} style={todo.completed ? decorationDone : {}}>
                        <td>{todo.id}</td>
                        <td>{todo.name}</td>
                        <td><input type="checkbox" defaultChecked={todo.completed}></input></td>
                        <td><button onClick={ () => onDelete(todo.id)}>Eliminar</button></td>
                        <td><button disabled={todo.completed} onClick={ () => onEdit(todo)}>Editar</button></td>

                    </tr>
                })}
            </tbody>
        </table>
    </div>
}