export const actionType = {
    LIST_CREATED: "list.LIST_CREATED",
    LIST_FINDED: "list.LIST_FINDED",
    LIST_DELETED: "list.LIST_DELETED"
};

export default {
    saved: (item) => ({type: actionType.LIST_CREATED, item}),
    finded: (item) => ({type: actionType.LIST_FINDED, item}),
    deleted: (item) => ({type: actionType.LIST_DELETED, item}),
};