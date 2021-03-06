/*
 * Copyright 2015 The SageTV Authors. All Rights Reserved.
 *
 * Adapted from Ogle - A video player
 * Copyright (C) 2000, 2001 Håkan Hjort
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111, USA.
 */
package sage.dvd;

public class vts_tmapt_t extends Struct{
  public Unsigned16 nr_of_tmaps = new Unsigned16();
  public Unsigned16 zero_1 = new Unsigned16();
  public Unsigned32 last_byte = new Unsigned32();
  public Unsigned32[] tmap_offset; /* offset table for each tmap */
  public vts_tmap_t[] tmap;

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("nr_of_tmaps: ");
    sb.append(nr_of_tmaps.get());
    sb.append('\n');
    if(tmap!=null)
    {
      for(int i=0;i<nr_of_tmaps.get();i++)
      {
        sb.append("tmap ");
        sb.append(i);
        sb.append(" : ");
        sb.append(tmap[i]);
        sb.append('\n');
      }
    }
    return sb.toString();
  }

  public boolean create()
  {
    if(nr_of_tmaps.get()!=0)
    {
      tmap_offset = (Unsigned32[]) array(new Unsigned32[nr_of_tmaps.get()]);
      tmap = new vts_tmap_t[nr_of_tmaps.get()];
      for(int i=0;i<nr_of_tmaps.get();i++)
      {
        tmap[i] = new vts_tmap_t();
        tmap[i].setByteBuffer(getByteBuffer(),
            getByteBufferPosition()+(int)tmap_offset[i].get());
        tmap[i].create();
      }
    }
    return true;
  }
};
//#define VTS_TMAPT_SIZE 8
