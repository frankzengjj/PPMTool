import {combineReducers} from "redux"
import errorReducer from "./errorReducers"
import projectReducers from "./projectReducers";

export default combineReducers({
    errors: errorReducer,
    project: projectReducers
})