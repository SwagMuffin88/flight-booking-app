import React from "react";
import FlightsTable from "../flights/FlightsTable";
import Hero from "../hero/Hero";

const Home = ({flights}) => {
    return (
      <div>
        <Hero/>
        <FlightsTable flights={flights}/>
      </div>
    )
}

export default Home