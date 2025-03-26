import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from 'react';
import api from './api/AxiosConfig'
import Layout from "./components/Layout";
import {Routes, Route} from 'react-router-dom';
import Home from './components/home/Home';
import FlightInfo from './components/flights/FlightInfo';

function App() {

  const [flights, setFlights] = useState([]);

  const getFlights = async () => {

    try {
      const response = await api.get("/flights/all")

      console.log(response.data)
      setFlights(response.data);

    } catch(err) {
      console.log(err);
    }
  }

  useEffect(() => {
    getFlights();
  }, [])

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Layout/>}>
          <Route path="/" element={<Home flights={flights} />} />
          <Route path="flights/:id" element={<FlightInfo />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
