import React, { useState, useEffect } from "react";
import { Items } from "./Items";
import apiURL from "../api";

export function ItemsList() {
  const [items, setItems] = useState([]);
  const [updateItem, setUpdateItem] = useState(false);
  const [numRows, setNumRows] = useState(0);

  const countItemsBy3 = () => {
    setNumRows(Math.ceil(items.length / 3));
  };

  async function fetchItems() {
    try {
      const response = await fetch(`${apiURL}/items`);
      const itemsData = await response.json();

      setItems(itemsData);
    } catch (err) {
      console.log("Oh no an error! ", err);
    }
  }

  useEffect(() => {
    fetchItems();
    countItemsBy3();
  }, [updateItem]);

  return (
    <>
      <h2>Item List</h2>
      <div className="card-columns">
        <div className="container flexrow">
          {items.map((item, idx) => {
            return (
              <div className="card">
                <Items
                  item={item}
                  setItems={setItems}
                  setUpdateItem={setUpdateItem}
                  updateItem={updateItem}
                  key={idx}
                />
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
}
