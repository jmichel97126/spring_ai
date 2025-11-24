-- Active l'extension vector si elle n'existe pas (nécessaire pour les embeddings vectoriels)
CREATE EXTENSION IF NOT EXISTS vector;

-- Active l'extension hstore si elle n'existe pas (permet de stocker des paires clé/valeur dans une colonne)
CREATE EXTENSION IF NOT EXISTS hstore;

-- Active l'extension uuid-ossp si elle n'existe pas (permet de générer des UUID avec uuid_generate_v4())
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Crée la table vector_store si elle n'existe pas
CREATE TABLE IF NOT EXISTS vector_store (
    -- Colonne id : UUID généré automatiquement avec uuid_generate_v4(), utilisé comme clé primaire
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    
    -- Colonne content : texte libre pour stocker le contenu
    content TEXT,
    
    -- Colonne metadata : données supplémentaires au format JSON
    metadata JSON,
    
    -- Colonne embedding : vecteur de dimension 1536 (typique pour des embeddings OpenAI)
    embedding VECTOR(1536)
);

-- Crée un index HNSW sur la colonne embedding pour accélérer les recherches de similarité
-- vector_cosine_ops indique que la mesure de distance utilisée est le cosinus
CREATE INDEX ON vector_store USING HNSW (embedding vector_cosine_ops);