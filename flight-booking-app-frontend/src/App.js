import './App.css';
import {useEffect, useState} from 'react';
import api from './api/AxiosConfig'
import Layout from "./components/Layout";
import {Routes, Route} from 'react-router-dom';
import Home from './components/home/Home';
import FlightInfo from './components/flights/FlightInfo';
import FlightSeats from './components/flights/FlightSeats';

function App() {

  const [flights, setFlights] = useState([]);

  const getFlights = async () => {

    try {
      const response = await api.get("/flights/all")
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
          <Route index element={<Home flights={flights} />} />
          <Route path="flights/flight/:id" element={<FlightInfo />} >
            {/*<Route index element={<FlightSeats />} />*/}
          </Route>
        </Route>
      </Routes>
    </div>
  );
}

export default App;
