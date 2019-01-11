with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with Cubiertos; use Cubiertos; -- Para cargar la especificacion y la clase Cubiertos

procedure Problema_Filosofos is
    N_Cubiertos: Positive; -- Variable entera positiva N_Cubiertos
    
    -- Muy parecido al typedef de c y c++
    type PCubierto is access Cubierto;
    
    -- Declaracion del tipo protegido TFilosofo (Parecido a hilo con envoltura de clase en java)
    task type TFilosofo(Id: Character; Cubierto_Izquierda: PCubierto; Cubierto_Derecha: PCubierto);
    
    -- Deficion de la clase y sus metodos
    task body TFilosofo is
        -- Definicion de como comé un filosofo
        procedure Comer is
        begin
            Coger(Cubierto_Izquierda.all);
            Coger(Cubierto_Derecha.all);
            Put(Id & "c "); delay 1.0;
            Soltar(Cubierto_Derecha.all);
            Soltar(Cubierto_Izquierda.all);
        end Comer;
        
        -- Definicion de como piensa un filosofo
        Procedure Pensar is
        begin
            Put(Id & "p ");
            delay 3.0;
        end Pensar;
    begin
        loop
            Comer; Pensar; -- El filosofo comé y piensa hasta el infinito
                           -- Como los politicos, pero sin la parte de pensar...
        end loop;
    end TFilosofo;
    
begin
    Put("Número de Filosofos: "); Get(N_Cubiertos); New_line; -- Para obtener la cantidad de filosofos que queramos
    
    declare
        type PTFilosofo is access TFilosofo;
        P: PTFilosofo;
        C: Character := 'A';
        Cuberteria: array (1..N_Cubiertos) of PCubierto;
    begin
        for i in 1..N_Cubiertos loop -- Se crean los cubiertos hasta un maximo que nosotros indiquemos en el programa
            Cuberteria(i) := new Cubierto;
        end loop;
        
        for i in 1..N_Cubiertos-1 loop -- Se crean los filosofos hasta un maximo que nosotros indiquemos en el programa
            P := new TFilosofo(C, Cuberteria(i), Cuberteria(i+1));
            C := Character'Succ(C);
        end loop;
        
        -- Se crea un filosofo que accede siempre al primer y al ultimo cubierto del array
        P := new TFilosofo(C, Cuberteria(1), Cuberteria(N_Cubiertos));
    end;
end Problema_Filosofos;
