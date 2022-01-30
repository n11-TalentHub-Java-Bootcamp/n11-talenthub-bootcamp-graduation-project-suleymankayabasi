import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import Form from "./routes/Form";
import DeleteForm from "./routes/forms/DeleteForm";
import UpdateForm from "./routes/forms/UpdateForm";
import UserActions from "./routes/UserActions";
import QueryForm from "./routes/forms/QueryForm";

ReactDOM.render(
	<BrowserRouter>
		<Routes>
			<Route path='/' element={<App />} />
			<Route path='application' element={<Form />} />
			<Route path='actions/query' element={<QueryForm />} />
			<Route path='actions/delete' element={<DeleteForm />} />
			<Route path='actions/update' element={<UpdateForm />} />
			<Route path='actions' element={<UserActions />} />
		</Routes>
	</BrowserRouter>,
	document.getElementById("root")
);
