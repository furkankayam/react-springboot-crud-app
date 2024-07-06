import React from "react";
import { FaReact } from "react-icons/fa";
import { GoPlus } from "react-icons/go";
import { PiGithubLogo } from "react-icons/pi";
import { SiSpringboot } from "react-icons/si";

const Header = () => {
  return (
    <>
      <div className="w-[90%] h-[50px] my-10 rounded-xl bg-[#374151] mx-auto flex justify-between items-center selection:bg-white selection:text-[#0b1623]">
        <a
          href="/"
          className="text-white font-medium ml-5 hover:text-[#d6ba8f] duration-300 flex items-center"
        >
          <FaReact className="text-[#09DBFB]" />
          <GoPlus />
          <SiSpringboot className="text-[#7ABE25]" />
          <p className="indent-2">CRUD APP</p>
        </a>

        <a
          className="text-white text-2xl mr-5 hover:text-[#d6ba8f] duration-300"
          href="https://github.com/furkankayam/react-springboot-crud"
          target="_blank"
        >
          <PiGithubLogo />
        </a>
      </div>
    </>
  );
};

export default Header;
